package person.mochi.tool.file;

import person.mochi.tool.file.exception.CanNotWriteException;
import person.mochi.tool.file.exception.NotAFileException;

import java.io.*;

public class MochiFileWriter {

    private File path;
    private File file;
    private InputStream is;
    private OutputStream os;
    private OutputStreamWriter bw;
    private FileWriter fileWriter;

    public MochiFileWriter(String path) throws IOException, NotAFileException {
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
        bw = new FileWriter(file, true);
    }

    public MochiFileWriter(File _file, boolean append) throws IOException, NotAFileException {
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
//		bw = new BufferedWriter(new FileWriter(file, append));
    }

    public MochiFileWriter(String path, boolean append) throws IOException, NotAFileException {
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
//		bw = new BufferedWriter(new FileWriter(file, append));
    }

    public void write(byte[] source) throws IOException, CanNotWriteException {
        if (file.canWrite()) {

            bw.flush();
        } else {
            throw new CanNotWriteException();
        }
    }

    public void close() {
        try {
            is.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, CanNotWriteException, NotAFileException {
        MochiFileWriter mf = new MochiFileWriter("/Users/zhangyimeng/iPhone X.txt", false);
    }

}
