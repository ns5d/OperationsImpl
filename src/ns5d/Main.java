package ns5d;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
            System.out.println("run as:");
			System.out.println("java Main ./data/test.log");
			System.out.println("java Main ./data/testlog.log");
			System.exit(-1);
		}
		
		String filename = args[0];
		if (!new File(filename).isFile()) {
			System.out.println("file not exists!");
		}
		
		if (!new File(filename).canRead()) {
			System.out.println("file can't read!");
		}
		
		LogStat logStat = new LogStat(filename);
		logStat.parseLog();
		//logStat.showLineMap();
		logStat.showStatMap();
		
		//test();
	}
	
	public static void test() {		
//		String s = "2015-10-28T12:25:16,026 TRACE [OperationsImpl] entry with (getData:97991)";
//		String arr[] = s.split(" ");
//		Line line1 = new Line(arr);
//		System.out.println(line1.toString());
			
//		LogStat logStat = new LogStat();
//		logStat.parseLog();
//		//logStat.showHashMap("line");
//		logStat.showHashMap("stat");
		
		
//		Stat stat = new Stat("1", 2003);
//		stat.addStat("2", 4318);
//		stat.addStat("3", 3338);
//		System.out.println(stat.toString());
	}
}