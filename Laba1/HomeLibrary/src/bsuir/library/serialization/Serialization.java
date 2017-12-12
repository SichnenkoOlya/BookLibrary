package bsuir.library.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bsuir.library.serialization.exception.SerializationException;

public class Serialization {

	public static <T> void serialize(ArrayList<T> list, String fileDBName) throws SerializationException {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(fileDBName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new SerializationException(e.getLocalizedMessage());
		} catch (IOException ioe) {
			throw new SerializationException(ioe.getLocalizedMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> deserialize(String fileDBName)throws SerializationException {
		ArrayList<T> list = new ArrayList<T>();
		try {
			FileInputStream fis = new FileInputStream(fileDBName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<T>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			throw new SerializationException(ioe.getLocalizedMessage());
		} catch (ClassNotFoundException c) {
			throw new SerializationException(c.getLocalizedMessage());
		}
		return list;
	}

}
