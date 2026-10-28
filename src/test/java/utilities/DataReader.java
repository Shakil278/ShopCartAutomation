
package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataReader {

    private final ObjectMapper mapper = new ObjectMapper();

    public LoginDataSet getLoginData() throws IOException {

        String path = System.getProperty("user.dir")
                + "/src/test/resources/testdata/loginData.json";

        File file = new File(path);

        LoginDataSet data = mapper.readValue(file, LoginDataSet.class);

        return data;
    }
}
