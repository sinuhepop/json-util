package tk.spop.json.spring.el;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonString;
import javax.json.JsonValue;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.common.LiteralExpression;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import tk.spop.json.common.JsonCloner;

@Data
@RequiredArgsConstructor
public class ExpressionProcessor extends JsonCloner {

	private final ExpressionResolver resolver;
	private final Object root;

	private boolean modified = false;
	private List<String> unresolved = new ArrayList<>();

	public boolean hasUnresolved() {
		return !unresolved.isEmpty();
	}

	@Override
	public void visitString(Context ctx, JsonString string) {
		val exp = resolver.parse(string.getString());
		if (!(exp instanceof LiteralExpression)) {
			try {
				JsonValue result = resolver.evaluate(exp, root);
				ctx.yield(result);
				modified = true;
				return;
			} catch (EvaluationException e) {
				unresolved.add(e.getExpressionString());
			}
		}

		super.visitString(ctx, string);
	}

}
