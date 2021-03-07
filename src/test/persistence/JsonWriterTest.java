package persistence;

import model.Leaderboard;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeaderboard.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeaderboard.json");
            lb = reader.read();
            assertEquals(0, lb.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Leaderboard lb = new Leaderboard();
            lb.addPlayer(new Player("Felix", 10));
            lb.addPlayer(new Player("Peter", 5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeaderboard.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLeaderboard.json");
            lb = reader.read();
            assertEquals(2, lb.getSize());
            checkPlayer("Felix", 10, lb.getPlayer(0));
            checkPlayer("Peter", 5, lb.getPlayer(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}