package name.admitriev.testing.rcd.data;

public class DictionaryData implements Comparable<DictionaryData>{

    private String name;
    private String description;
    private String expectedResult;

    public DictionaryData() {
    }

    public String getName() {
        return name;
    }

    public DictionaryData withName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DictionaryData withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public DictionaryData withExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryData dictionaryData = (DictionaryData) o;

        if (description != null ? !description.equals(dictionaryData.description) : dictionaryData.description != null)
            return false;
        if (name != null ? !name.equals(dictionaryData.name) : dictionaryData.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(DictionaryData o) {
        return name.toLowerCase().compareTo(o.name.toLowerCase());
    }

    @Override
    public String toString() {
        return "DictionaryData{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", expectedResult='" + expectedResult + '\'' +
               '}';
    }
}
