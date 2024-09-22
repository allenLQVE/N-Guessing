import java.io.Serializable;

public class Player implements Serializable {
    private int highestSuccess;
    private int[] record;
    private String name;

    public Player() {
        this(0, new int[10], "Unknown");
    }

    public Player(String name) {
        this(0, new int[10], name);
    }

    public Player(int highestSuccess, int[] record, String name) {
        this.highestSuccess = highestSuccess;
        this.record = record;
        this.name = name;
    }
    
    public boolean equals(Player player){
        return player.name == name;
    }

    public void updateRecord(int digit, int attempts){
        //if the digit of guessing is over the record list, update the record list size
        if(digit >= record.length){
            int[] newRecord = new int[digit * 2];
            System.arraycopy(record, 0, newRecord, 0, record.length);
            record = newRecord;
        }

        //if there is not record or the recorded attempts are larger, update
        if(record[digit] == 0 || attempts < record[digit]){
            record[digit] = attempts;
        }

        if(digit > highestSuccess){
            highestSuccess = digit;
        }
    }

    public int getRecordOfDigit(int digit){
        if(digit < record.length){
            return record[digit];
        }
        return 0;
    }

    public int getHighestSuccess() {
        return highestSuccess;
    }

    public String getName() {
        return name;
    }

    public int[] getRecord() {
        return record;
    }

    public void setHighestSuccess(int highestSuccess) {
        this.highestSuccess = highestSuccess;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecord(int[] record) {
        this.record = record;
    }
}
