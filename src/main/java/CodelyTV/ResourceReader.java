package CodelyTV;
import java.io.IOException;
import java.util.List;

public interface ResourceReader {
     String askUserForTheValue();
     int askUserForTheNumberOfResults();
     List<String> getResult() throws Exception;
}
