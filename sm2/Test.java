package sm2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

	public final static int NUM = 100;
	public final static int REP = 20;

	public final static List<Item> REPEATS = new CopyOnWriteArrayList<Item>();

	public static void main(String[] args) {
		List<Item> items = new CopyOnWriteArrayList<Item>();
		for (int i = 0; i < NUM; i++) {
			Item item = new Item();
			item.ef = 2.5f;
			item.n = 1;
			item.q = 1;
			items.add(item);
		}
		Random rand = new Random();
		for (int i = 1; i < REP; i++) {
			REPEATS.clear();
			while (!items.isEmpty()) {
				Item item = items.remove(0);
				if (item.q < 4) {
					int q = rand.nextInt(6);
					item.q = q;
					if (q > 2) {
						item.n = getN(i, item.ef);
					} else {
						item.n = 1;
					}
					item.ef = item.ef
							+ (float) (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02));
					item.ef = item.ef < 1.3 ? 1.3f : item.ef;
					REPEATS.add(item);
				}
			}
			System.out.println(REPEATS.size() + "->" + REPEATS);
			if (REPEATS.isEmpty()) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! finished " + i);
				break;
			} else {
				items.addAll(REPEATS);
			}
		}
	}

	public static int getN(int n, float ef) {
		n = n > 0 ? n : 1;
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 6;
		} else {
			return (int) (getN(n - 1, ef) * ef);
		}
	}
}
