package tk.spop.json.spring.el;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.json.JsonValue;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Data
@RequiredArgsConstructor
public class ExpressionResolver {

	private final Map<String, Expression> cache = new ConcurrentHashMap<>();
	private final ExpressionParser parser;
	private final TemplateParserContext ctx;

	private boolean allowUnresolved = false;
	private boolean nullWhenUnexisting = false;

	public ExpressionResolver() {
		this(new SpelExpressionParser(), new TemplateParserContext());
	}

	@SuppressWarnings("unchecked")
	public <T extends JsonValue> T resolvePath(JsonValue value, String path) {
		val exp = cache.computeIfAbsent(path, parser::parseExpression);
		return (T) exp.getValue(getEvaluationContext(value), JsonValue.class);
	}

	public Expression parse(String template) {
		return cache.computeIfAbsent(template, t -> parser.parseExpression(template, ctx));
	}

	// public String evaluate(String templateExpression, Object root) {
	// val exp = cache.computeIfAbsent(templateExpression, t ->
	// parser.parseExpression(templateExpression, ctx));
	// try {
	// return exp.getValue(root, String.class);
	// } catch (EvaluationException e) {
	// System.out.println(e);
	// return null;
	// }
	// }

	@SuppressWarnings("unchecked")
	public <T> T evaluate(Expression exp, Object root) {
		return (T) exp.getValue(getEvaluationContext(root));
	}

	public JsonValue resolve(JsonValue value) {
		ExpressionProcessor processor;
		do {
			processor = new ExpressionProcessor(this, value);
			value = processor.process(value);
		} while (processor.isModified());
		if (!allowUnresolved && processor.hasUnresolved()) {
			throw new EvaluationException("Unresolved expressions: " + processor.getUnresolved());
		}

		return value;
	}

	protected EvaluationContext getEvaluationContext(Object root) {
		val ctx = new StandardEvaluationContext(root);
		ctx.addPropertyAccessor(new MapAccessor());
		return ctx;
	}

}
