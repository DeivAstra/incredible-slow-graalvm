import java.util.ArrayList;
import java.util.List;

// Results (AMD Ryzen 7 5800H)

// JDK 17 (OpenJDK 64-Bit Server VM GraalVM CE 21.3.0 (build 17.0.1+12-jvmci-21.3-b05, mixed mode, sharing)
// generateRandomData >
// end generateRandomData (2937 ms)
// search synonyms in data >
// end search synonyms (4564 ms)

// GraalVm (graalvm-ce-java17-21.3.0)
// generateRandomData >
// end generateRandomData (30199 ms)
// search synonyms in data >
// end search synonyms (11252 ms)

// GraalVm (GraalVM CE 22.1.0-dev-20220113_2228)
// generateRandomData >
// end generateRandomData (59646 ms)
// search synonyms in data >
// end search synonyms (13237 ms)

public class IncredibleSlowGraalVmTest {

	public static void main(final String[] args) throws Exception {

		final IncredibleSlowGraalVmTest encoder = new IncredibleSlowGraalVmTest();

		final int deep = 2;  // min 1
		final int inputSynonymsCount = 100000; // min 1

		long start = System.currentTimeMillis();
		log("generateRandomData >");
		final int[][] data = encoder.generateRandomData(deep);
		long end = System.currentTimeMillis() - start;
		log("end generateRandomData (" + end + " ms)");

		// input encoded data
		final int[] synonyms = generateRandomSynonyms(data, inputSynonymsCount, deep);
		log("search synonyms in data >");
		start = System.currentTimeMillis();

		for (int i = 0; i < synonyms.length; i++) {
			final int result = getCharBySynonym(data, synonyms[i]);
			// log(result);
		}

		end = System.currentTimeMillis() - start;
		log("end search synonyms (" + end + " ms)");
	}

	private int[][] generateRandomData(final int deep) throws Exception {

		final List<Integer> donorSynonyms = getDonorList(Character.MAX_CODE_POINT);
		final List<Integer> donorChars = getDonorList(Character.MAX_VALUE);

		final int[][] data = new int[Character.MAX_VALUE][deep];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (j == data[0].length - 1) {
					final int charRnd = getRandomNumber(0, donorChars.size());
					data[i][j] = donorChars.remove(charRnd);
				} else {
					final int synonymRnd = getRandomNumber(0, donorSynonyms.size());
					data[i][j] = donorSynonyms.remove(synonymRnd);
				}
			}
		}
		return data;
	}

	private static int getRandomNumber(final int min, final int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	private List<Integer> getDonorList(final int maxLength) {
		final List<Integer> list = new ArrayList<>(maxLength);
		for (int i = 0; i <= maxLength; i++) {
			list.add(i);
		}
		return list;
	}


	private static void log(final Object o) {
		System.out.println(o == null ? "null" : o.toString());
	}

	private static int getCharBySynonym(final int[][] data, final int synonym) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] == synonym)
					return data[i][data[0].length - 1];
			}
		}
		throw new RuntimeException("Synonym not found: " + synonym);
	}

	private static int[] generateRandomSynonyms(final int[][] data, final int length, final int deep) {
		final int[] ints = new int[length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = data[getRandomNumber(0, Character.MAX_VALUE)][getRandomNumber(0, deep)];
		}
		return ints;
	}
}
