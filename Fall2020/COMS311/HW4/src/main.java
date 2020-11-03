import java.io.IOException;
public class main {
public static void main(String[] args) throws IOException {
RobotPath rPath = new RobotPath();
rPath.readInput("tests/Grid10.txt");
System.out.println("\n planShortest:\n");
rPath.planShortest();
rPath.output();
System.out.println("\n quickPlan:\n");
rPath.quickPlan();
rPath.output();
}
}