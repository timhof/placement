package org.tfa.transformers;

import java.io.IOException;

import org.tfa.model.HiringManager.EntityType;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class EnumAdapterFactory implements TypeAdapterFactory {

	    @Override
	    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
	            Class<? super T> rawType = type.getRawType();
	            if (rawType == EntityType.class) {
	                return new EntityTypeEnumAdapter<T>();
	            }
	            return null;
	    }

	    public class EntityTypeEnumAdapter<T> extends TypeAdapter<T> {

	         public void write(JsonWriter out, T value) throws IOException {
	              if (value == null) {
	                   out.nullValue();
	                   return;
	              }
	              EntityType entityType = (EntityType) value;
	              // Here write what you want to the JsonWriter. 
	              out.beginObject();
	              out.name("label");
	              out.value(entityType.getLabel());
	              out.endObject();
	         }

	         public T read(JsonReader in) throws IOException {
	              // Properly deserialize the input (if you use deserialization)
	              return null;
	         }
	    }

	}
