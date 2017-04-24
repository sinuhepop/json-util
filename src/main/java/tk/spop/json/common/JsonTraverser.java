package tk.spop.json.common;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

public interface JsonTraverser<C> {

	void visitValue(C ctx, JsonValue value);

	void visitArray(C ctx, JsonArray array);

	void visitObject(C ctx, JsonObject object);

	void visitString(C ctx, JsonString string);

	void visitNumber(C ctx, JsonNumber number);

	void visitBoolean(C ctx, boolean b);

	void visitNull(C ctx);

}
