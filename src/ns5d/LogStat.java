package ns5d;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogStat {	
	String filename;
	Map<String, Line> lineMap = new HashMap<>(); // <MethodName+MethodID, Line>
	Map<String, Stat> statMap = new HashMap<>(); // <MethodName, Stat>
	
	LogStat(String filename) {
		this.filename = filename;
	}
	
	LogStat() {
		this.filename = "./data/test.log";
	};
	
	/**
	 * Парсинг лог-файла
	 */
	public void parseLog() {		
		BufferedReader br = null;
		
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(this.filename));

			while ((sCurrentLine = br.readLine()) != null) {
				String lineArr[] = sCurrentLine.split(" ");
				if (lineArr.length == 6) // True formated
					updateLineMap(lineArr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}

	/**
	 * Обновление таблицы Строк
	 * @param lineArr - массив слов строки
	 */
	private void updateLineMap(String [] lineArr) {
		Line line = new Line(lineArr);
		String key = line.getMethodName().concat(line.getMethodID());
		
		if (lineMap.containsKey(key)) { // remove
			if (line.getIsEntryExit() == Line.EntryExit.exit) {
				Line entryLine = (Line)lineMap.get(key);
				long time = line.getDateDiff(entryLine.getDate());
				updateStatMap(line.getMethodName(), line.getMethodID(), time);
				lineMap.remove(key);
			}
		} else { // add
			if (line.getIsEntryExit() == Line.EntryExit.entry) {
				lineMap.put(key, line);
			}
		}
	}
	
	/**
	 * Обновление таблицы Статистики
	 * @param MethodName - название метода
	 * @param MethodID - номер метода
	 * @param time - время выполнения метода
	 */
	private void updateStatMap(String MethodName, String MethodID, long time) {
		if (statMap.containsKey(MethodName)) { // update exist
			Stat stat = statMap.get(MethodName);
			stat.addStat(MethodID, time);
		} else { // add new
			Stat stat = new Stat(MethodID, time);
			statMap.put(MethodName, stat);
		}
	}
	
	/**
	 * Просмотр таблицы Строк
	 */
	public void showLineMap() {
		//System.out.println("lineMap:");
		for (Map.Entry<String, Line> entry : lineMap.entrySet()) {
		    System.out.println("Key: " + entry.getKey());
		    Line line = (Line)entry.getValue();
	    	System.out.println(line.toString());
            System.out.println(line.getDate());
            System.out.println(line.getIsEntryExit().toString());
            System.out.println(line.getMethodID());
			System.out.println("=========================");
		}
	}
	
	/**
	 * Просмотр таблицы Статистики
	 */
	public void showStatMap() {
		//System.out.println("statMap:");
		for (Map.Entry<String, Stat> entry : statMap.entrySet()) {
		    System.out.print("OperationsImpl:" + entry.getKey() + " ");
		    Stat stat = (Stat)entry.getValue();
	    	System.out.println(stat.toString());
		}
	}
}
