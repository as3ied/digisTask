package net.senior.divistask;

import com.google.gson.Gson;

import net.senior.digistask.MainActivity;
import net.senior.digistask.model.Colors;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest {
    @Test
    public void testColor(){
        Colors colors = new Gson().fromJson("{\n" +
                "\t\"RSRP\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"Min\",\n" +
                "\t\t\t\"To\": \"-110\",\n" +
                "\t\t\t\"Color\": \"#000A00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-110\",\n" +
                "\t\t\t\"To\": \"-100\",\n" +
                "\t\t\t\"Color\": \"#E51304\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-100\",\n" +
                "\t\t\t\"To\": \"-90\",\n" +
                "\t\t\t\"Color\": \"#FAFD0C\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-90\",\n" +
                "\t\t\t\"To\": \"-80\",\n" +
                "\t\t\t\"Color\": \"#02FA0E\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-80\",\n" +
                "\t\t\t\"To\": \"-70\",\n" +
                "\t\t\t\"Color\": \"#0B440D\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-70\",\n" +
                "\t\t\t\"To\": \"-60\",\n" +
                "\t\t\t\"Color\": \"#0EFFF8\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-60\",\n" +
                "\t\t\t\"To\": \"Max\",\n" +
                "\t\t\t\"Color\": \"#0007FF\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"RSRQ\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"Min\",\n" +
                "\t\t\t\"To\": \"-19.5\",\n" +
                "\t\t\t\"Color\": \"#000000\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-19.5\",\n" +
                "\t\t\t\"To\": \"-14\",\n" +
                "\t\t\t\"Color\": \"#ff0000\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-14\",\n" +
                "\t\t\t\"To\": \"-9\",\n" +
                "\t\t\t\"Color\": \"#ffee00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-9\",\n" +
                "\t\t\t\"To\": \"-3\",\n" +
                "\t\t\t\"Color\": \"#80ff00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"-3\",\n" +
                "\t\t\t\"To\": \"Max\",\n" +
                "\t\t\t\"Color\": \"#3f7806\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"SINR\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"Min\",\n" +
                "\t\t\t\"To\": \"0\",\n" +
                "\t\t\t\"Color\": \"#000000\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"0\",\n" +
                "\t\t\t\"To\": \"5\",\n" +
                "\t\t\t\"Color\": \"#F90500\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"5\",\n" +
                "\t\t\t\"To\": \"10\",\n" +
                "\t\t\t\"Color\": \"#FD7632\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"10\",\n" +
                "\t\t\t\"To\": \"15\",\n" +
                "\t\t\t\"Color\": \"#FBFD00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"15\",\n" +
                "\t\t\t\"To\": \"20\",\n" +
                "\t\t\t\"Color\": \"#00FF06\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"20\",\n" +
                "\t\t\t\"To\": \"25\",\n" +
                "\t\t\t\"Color\": \"#027500\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"25\",\n" +
                "\t\t\t\"To\": \"30\",\n" +
                "\t\t\t\"Color\": \"#0EFFF8\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"From\": \"30\",\n" +
                "\t\t\t\"To\": \"Max\",\n" +
                "\t\t\t\"Color\": \"#0000F0\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}", Colors.class);
        Assert.assertEquals("#3f7806", MainActivity.getColor("RSRQ",colors.getRSRQ(),-4));
    }
}
