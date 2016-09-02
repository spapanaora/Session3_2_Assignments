package acadgild.session3.task5;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileRead {
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.out.println("Pass required arguments");
			System.exit(1);
		}

		Path path = new Path(args[0]);
		Configuration conf = new Configuration();
		try 
		{
			FileSystem fs = FileSystem.get(path.toUri(), conf);
			FSDataInputStream in = fs.open(path);
			IOUtils.copyBytes(in, System.out, 4096, false);
			IOUtils.closeStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
