package name.admitriev.testing.dadata.data;

public class PhoneData {
	private String phone = "";
	private String type = "";
	private String region = "";
	private String operator = "";

	public String getPhone() {
		return phone;
	}

	public String getType() {
		return type;
	}

	public String getRegion() {
		return region;
	}

	public String getOperator() {
		return operator;
	}


	public PhoneData withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public PhoneData withType(String type) {
		this.type = type;
		return this;
	}

	public PhoneData withRegion(String region) {
		this.region = region;
		return this;
	}

	public PhoneData withOperator(String operator) {
		this.operator = operator;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PhoneData phoneData = (PhoneData) o;

		if (operator != null ? !operator.equals(phoneData.operator) : phoneData.operator != null)
			return false;
		if (phone != null ? !phone.equals(phoneData.phone) : phoneData.phone != null)
			return false;
		if (region != null ? !region.equals(phoneData.region) : phoneData.region != null)
			return false;
		if (type != null ? !type.equals(phoneData.type) : phoneData.type != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = phone != null ? phone.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (region != null ? region.hashCode() : 0);
		result = 31 * result + (operator != null ? operator.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PhoneData{" +
		       "phone='" + phone + '\'' +
		       ", type='" + type + '\'' +
		       ", region='" + region + '\'' +
		       ", operator='" + operator + '\'' +
		       '}';
	}
}
