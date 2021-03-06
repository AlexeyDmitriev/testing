package name.admitriev.wikitests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CSVDataLoader<T> {
	public List<T> loadDataFromCsvFile(File file) throws IOException {
		List<T> list = new ArrayList<>(10);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split(";");
				list.add(generateObject(parts));
				line = reader.readLine();
			}
			reader.close();
			return list;
		}
	}

	protected abstract T generateObject(String[] parts);
}
