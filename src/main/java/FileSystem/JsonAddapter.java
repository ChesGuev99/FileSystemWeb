package FileSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonAddapter {
    private String json = "";
    private ObjectMapper mapper = new ObjectMapper();
    private String relativePath = "src/main/java/FileSystem/folders.json";
    private String filePath = System.getProperty("user.dir") + File.separator + relativePath;

    public void saveJsonFolder(Folder folder) throws JsonProcessingException {
        json = mapper.writeValueAsString(folder);
        System.out.println(json);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveJsonFileSystem(MainFileSystem fs) throws JsonProcessingException {
        json = mapper.writeValueAsString(fs);
        System.out.println(json);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Folder loadJsonFolder() throws IOException {
        File file = new File(filePath);

        Folder object = null;
        object = mapper.readValue(file, Folder.class);
        System.out.println("Name= " + object.getName() + "   Date= " + object.getCreateDate() + "   User= " + object.getUser());
        return object;
    }

    public MainFileSystem loadJsonFileSystem() throws IOException {
        File file = new File(filePath);

        MainFileSystem object = null;
        object = mapper.readValue(file, MainFileSystem.class);
        return object;
    }
}
