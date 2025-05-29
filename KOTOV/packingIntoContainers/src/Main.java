import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

class Item {
    int mass;
    int volume;

    public Item(int mass, int volume) {
        this.mass = mass;
        this.volume = volume;
    }
}

class Container {
    static int nextId = 0;
    int id;
    ArrayList<Item> items = new ArrayList<>();
    int currentMass = 0;
    int currentVolume = 0;

    public Container() {
        this.id = nextId++;
    }

    public void add(Item item) {
        items.add(item);
        currentMass += item.mass;
        currentVolume += item.volume;
    }

    public int getRemainingMass() {
        return 100 - currentMass;
    }

    public int getRemainingVolume() {
        return 100 - currentVolume;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        int n = Integer.parseInt(reader.readLine());
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().split(" ");
            int mass = Integer.parseInt(parts[0]);
            int volume = Integer.parseInt(parts[1]);
            items.add(new Item(mass, volume));
        }
        items.sort((a, b) -> Integer.compare(Math.max(b.mass, b.volume), Math.max(a.mass, a.volume)));

        Comparator<Container> comparator = Comparator.comparingInt(Container::getRemainingMass)
                .thenComparingInt(c -> c.id);
        TreeSet<Container> containerSet = new TreeSet<>(comparator);
        ArrayList<Container> containers = new ArrayList<>();

        for (Item item : items) {
            Container dummy = new Container();
            dummy.currentMass = 100 - item.mass;
            dummy.id = -1;

            NavigableSet<Container> candidates = containerSet.tailSet(dummy, true);
            Container found = null;
            for (Container c : candidates) {
                if (c.getRemainingVolume() >= item.volume) {
                    found = c;
                    break;
                }
            }

            if (found != null) {
                containerSet.remove(found);
                found.add(item);
                containerSet.add(found);
            } else {
                Container newContainer = new Container();
                newContainer.add(item);
                containerSet.add(newContainer);
                containers.add(newContainer);
            }
        }

        writer.write(containers.size() + "\n");
        for (Container container : containers) {
            for (int j = 0; j < container.items.size(); j++) {
                Item item = container.items.get(j);
                writer.write(item.mass + ", " + item.volume);
                if (j < container.items.size() - 1) {
                    writer.write("; ");
                } else {
                    writer.write(";");
                }
            }
            writer.write("\n");
        }
        writer.close();
        reader.close();
    }
}