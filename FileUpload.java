package acadgild.session3.task6;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileUpload {
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.out.println("Pass required arguments");
			System.exit(1);
		}

		Path srcPath = new Path(args[0]);
		Path dstPath = new Path(args[1]);
		Configuration conf = new Configuration();
		try 
		{
			FileSystem fs = FileSystem.get(conf);
			fs.copyFromLocalFile(false, srcPath, dstPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
