
class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            if (!firstName.equals(""))
                this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            if (!lastName.equals(""))
                this.lastName = lastName;
        }
    }

    public String getFullName() {
        String result = "Unknown";

        if (firstName != null && lastName != null) {
            if (!firstName.equals("") && !lastName.equals("")) {
                result = firstName + " " + lastName;
            } else if (!firstName.equals("")) {
                result = firstName;
            } else if (!lastName.equals("")) {
                result = lastName;
            }

        }

        if (firstName == null && lastName != null) {
            if (!lastName.equals(""))
                result = lastName;
        }

        if (lastName == null && firstName != null) {
            if (!firstName.equals(""))
                result = firstName;
        }

        return result;
    }
}