package tk.spop.json.common;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import lombok.Getter;
import lombok.val;
import tk.spop.json.common.JsonCloner.Context;

public class JsonCloner extends AbstractJsonTraverser<Context> {

	public static class Context {

		@Getter
		public JsonValue value;

		public void yield(JsonValue value) {
			this.value = value;
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends JsonValue> T process(T value) {
		val ctx = new Context();
		visitValue(ctx, value);
		return (T) ctx.getValue();
	}

	@Override
	public void visitArray(Context ctx, JsonArray array) {
		val builder = Json.createArrayBuilder();
		array.forEach(value -> {
			visitValue(ctx, value);
			builder.add(ctx.getValue());
		});
		ctx.yield(builder.build());
	}

	@Override
	public void visitObject(Context ctx, JsonObject object) {
		val builder = Json.createObjectBuilder();
		object.forEach((name, value) -> {
			visitValue(ctx, value);
			builder.add(name, ctx.getValue());
		});
		ctx.yield(builder.build());
	}

	@Override
	public void visitString(Context ctx, JsonString string) {
		ctx.yield(string);
	}

	@Override
	public void visitNumber(Context ctx, JsonNumber number) {
		ctx.yield(number);
	}

	@Override
	public void visitBoolean(Context ctx, boolean b) {
		ctx.yield(b ? JsonValue.TRUE : JsonValue.FALSE);
	}

	@Override
	public void visitNull(Context ctx) {
		ctx.yield(JsonValue.NULL);
	}

}
