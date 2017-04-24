package tk.spop.json.common;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

public abstract class AbstractJsonTraverser<C> implements JsonTraverser<C> {

	@Override
	public void visitValue(C ctx, JsonValue value) {
		switch (value.getValueType()) {
		case ARRAY:
			this.visitArray(ctx, (JsonArray) value);
			break;
		case OBJECT:
			this.visitObject(ctx, (JsonObject) value);
			break;
		case STRING:
			this.visitString(ctx, (JsonString) value);
			break;
		case NUMBER:
			this.visitNumber(ctx, (JsonNumber) value);
			break;
		case TRUE:
			this.visitBoolean(ctx, true);
			break;
		case FALSE:
			this.visitBoolean(ctx, false);
			break;
		case NULL:
		default:
			this.visitNull(ctx);
		}
	}

	@Override
	public void visitArray(C ctx, JsonArray array) {
		array.forEach(value -> visitValue(ctx, value));
	}

	@Override
	public void visitObject(C ctx, JsonObject object) {
		object.forEach((key, value) -> visitValue(ctx, value));
	}

}
