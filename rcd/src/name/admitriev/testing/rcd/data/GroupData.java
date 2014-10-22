package name.admitriev.testing.rcd.data;

public class GroupData {
	private String name;

	public String getName() {
		return name;
	}

	public GroupData withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GroupData groupData = (GroupData) o;

		if (name != null ? !name.equals(groupData.name) : groupData.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
