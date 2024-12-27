package simple;

public class ScaleInfo {
	
    private int scalePos;
    private String scaleName;
    private boolean checked;

    public ScaleInfo() {}
    
    public ScaleInfo(int scalePos, String scaleName, boolean checked) {
        this.scalePos = scalePos;
        this.scaleName = scaleName;
        this.checked = checked;
    }


	public int getScalePos() {
        return scalePos;
    }

    public void setScalePos(int scalePos) {
        this.scalePos = scalePos;
    }

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "ScaleInfo{" +
                "scalePos=" + scalePos +
                ", scaleName='" + scaleName + '\'' +
                ", checked=" + checked +
                '}';
    }
    
}
