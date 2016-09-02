package acadgild.session3.task4;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TimeStampBasedFileListing {
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.out.println("Pass required arguments");
			System.exit(1);
		}

		Path path = new Path(args[0]);
		Configuration conf = new Configuration();
		Long start_ts = new Long(0); 
		Long end_ts = new Long(12233);
		try 
		{
			if(args.length == 2)
			{
				start_ts = Long.parseLong(args[1]);
			}
			if(args.length == 3)
			{
				end_ts = Long.parseLong(args[2]);
			}
			displayRecusivelsy(path, conf, start_ts, end_ts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void displayRecusivelsy(Path path, Configuration conf, Long start_ts, Long end_ts ) throws IOException 
	{
		FileSystem fs = FileSystem.get(path.toUri(), conf);
		FileStatus[] fStatuses = fs.listStatus(path);
		for(FileStatus fStatus : fStatuses)
		{
			if(fStatus.isDirectory())
			{
				displayRecusivelsy(fStatus.getPath(), conf, start_ts, end_ts);
			}
			else if(fStatus.isFile())
			{
				Long modTime = fStatus.getModificationTime();
				if(modTime > start_ts && modTime < end_ts)
				{
					System.out.println("The File path is :" + fStatus.getPath());
					System.out.println("The modification time is :" + fStatus.getModificationTime());
				}
			}
		}
	}
}
