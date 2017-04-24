package tk.spop.json.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.val;
import tk.spop.json.common.MapBuilder.Context;

public class MapBuilder extends AbstractJsonTraverser<Context> {

	@Setter
	@AllArgsConstructor
	public static class Context {

		private Consumer<Object> consumer;

		public void yield(Object o) {
			consumer.accept(o);
		}
	}

	@Override
	public void visitArray(Context ctx, JsonArray array) {
		val list = new ArrayList<Object>();
		ctx.yield(list);
		array.forEach(value -> {
			ctx.setConsumer(x -> list.add(x));
			this.visitValue(ctx, value);
		});
	}

	@Override
	public void visitObject(Context ctx, JsonObject object) {
		val map = new LinkedHashMap<String, Object>();
		ctx.yield(map);
		object.forEach((name, value) -> {
			ctx.setConsumer(x -> map.put(name, x));
			this.visitValue(ctx, value);
		});
	}

	@Override
	public void visitString(Context ctx, JsonString string) {
		ctx.yield(string.getString());
	}

	@Override
	public void visitNumber(Context ctx, JsonNumber number) {
		ctx.yield(number.bigDecimalValue());
	}

	@Override
	public void visitBoolean(Context ctx, boolean b) {
		ctx.yield(b);
	}

	@Override
	public void visitNull(Context ctx) {
	}

}
