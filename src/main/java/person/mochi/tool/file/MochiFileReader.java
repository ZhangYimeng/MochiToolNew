package person.mochi.tool.file;

import person.mochi.tool.file.exception.CanNotWriteException;
import person.mochi.tool.file.exception.NotAFileException;

import java.io.*;

public class MochiFileReader {

	private File path;
	private File file;
	private InputStream is;
	private BufferedReader br;

	public MochiFileReader(File _file) throws IOException, NotAFileException {
		file = _file;
		this.path = new File(file.getParent());
		if (!this.path.exists()) {
			this.path.mkdir();
		}
		if (!file.isDirectory()) {
			if (!file.exists()) {
				file.createNewFile();
			}
		} else {
			throw new NotAFileException();
		}
		is = new FileInputStream(_file);
		br = new BufferedReader(new FileReader(file));
	}
	
	public MochiFileReader(String path) throws IOException, NotAFileException {
		file = new File(path);
		this.path = new File(file.getParent());
		if (!this.path.exists()) {
			this.path.mkdir();
		}
		if (!file.isDirectory()) {
			if (!file.exists()) {
				file.createNewFile();
			}
		} else {
			throw new NotAFileException();
		}
		is = new FileInputStream(path);
		br = new BufferedReader(new FileReader(file));
	}

	public byte[] readAllContents() throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toByteArray();
	}

	public void resetIndex() throws FileNotFoundException {
		br = new BufferedReader(new FileReader(file));
	}

	public void close() {
		try {
			is.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, CanNotWriteException, NotAFileException {
		MochiFileReader mf = new MochiFileReader("pic/WechatIMG43.jpeg");
		System.out.print(mf.readAllContents().length);
		System.out.println("over");
	}

}
