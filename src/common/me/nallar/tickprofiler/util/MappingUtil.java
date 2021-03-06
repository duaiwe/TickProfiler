package me.nallar.tickprofiler.util;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import me.nallar.tickprofiler.Log;

public enum MappingUtil {
	;
	private static final Map<String, String> mappings = getMappings();

	private static Map<String, String> getMappings() {
		try {
			ObjectInputStream s = new ObjectInputStream(MappingUtil.class.getResourceAsStream("/mappings.obj"));
			try {
				//noinspection unchecked
				return (Map<String, String>) s.readObject();
			} finally {
				s.close();
			}
		} catch (Exception e) {
			Log.severe("Failed to load class mappings", e);
			return new HashMap<String, String>();
		}
	}

	public static String debobfuscate(String clazz) {
		String mapping = mappings.get(clazz);
		return mapping == null ? clazz : mapping;
	}
}
