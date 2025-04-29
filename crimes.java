// ORM class for table 'crimes'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Apr 10 01:12:38 MSK 2025
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class crimes extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.id = (Integer)value;
      }
    });
    setters.put("date", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.date = (java.sql.Timestamp)value;
      }
    });
    setters.put("block", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.block = (String)value;
      }
    });
    setters.put("primary_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.primary_type = (String)value;
      }
    });
    setters.put("location_description", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.location_description = (String)value;
      }
    });
    setters.put("arrest", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.arrest = (Boolean)value;
      }
    });
    setters.put("domestic", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.domestic = (Boolean)value;
      }
    });
    setters.put("beat", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.beat = (Integer)value;
      }
    });
    setters.put("district", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.district = (Integer)value;
      }
    });
    setters.put("ward", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.ward = (Double)value;
      }
    });
    setters.put("community_area", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.community_area = (Double)value;
      }
    });
    setters.put("fbi_code", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.fbi_code = (String)value;
      }
    });
    setters.put("x_coordinate", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.x_coordinate = (Double)value;
      }
    });
    setters.put("y_coordinate", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        crimes.this.y_coordinate = (Double)value;
      }
    });
  }
  public crimes() {
    init0();
  }
  private Integer id;
  public Integer get_id() {
    return id;
  }
  public void set_id(Integer id) {
    this.id = id;
  }
  public crimes with_id(Integer id) {
    this.id = id;
    return this;
  }
  private java.sql.Timestamp date;
  public java.sql.Timestamp get_date() {
    return date;
  }
  public void set_date(java.sql.Timestamp date) {
    this.date = date;
  }
  public crimes with_date(java.sql.Timestamp date) {
    this.date = date;
    return this;
  }
  private String block;
  public String get_block() {
    return block;
  }
  public void set_block(String block) {
    this.block = block;
  }
  public crimes with_block(String block) {
    this.block = block;
    return this;
  }
  private String primary_type;
  public String get_primary_type() {
    return primary_type;
  }
  public void set_primary_type(String primary_type) {
    this.primary_type = primary_type;
  }
  public crimes with_primary_type(String primary_type) {
    this.primary_type = primary_type;
    return this;
  }
  private String location_description;
  public String get_location_description() {
    return location_description;
  }
  public void set_location_description(String location_description) {
    this.location_description = location_description;
  }
  public crimes with_location_description(String location_description) {
    this.location_description = location_description;
    return this;
  }
  private Boolean arrest;
  public Boolean get_arrest() {
    return arrest;
  }
  public void set_arrest(Boolean arrest) {
    this.arrest = arrest;
  }
  public crimes with_arrest(Boolean arrest) {
    this.arrest = arrest;
    return this;
  }
  private Boolean domestic;
  public Boolean get_domestic() {
    return domestic;
  }
  public void set_domestic(Boolean domestic) {
    this.domestic = domestic;
  }
  public crimes with_domestic(Boolean domestic) {
    this.domestic = domestic;
    return this;
  }
  private Integer beat;
  public Integer get_beat() {
    return beat;
  }
  public void set_beat(Integer beat) {
    this.beat = beat;
  }
  public crimes with_beat(Integer beat) {
    this.beat = beat;
    return this;
  }
  private Integer district;
  public Integer get_district() {
    return district;
  }
  public void set_district(Integer district) {
    this.district = district;
  }
  public crimes with_district(Integer district) {
    this.district = district;
    return this;
  }
  private Double ward;
  public Double get_ward() {
    return ward;
  }
  public void set_ward(Double ward) {
    this.ward = ward;
  }
  public crimes with_ward(Double ward) {
    this.ward = ward;
    return this;
  }
  private Double community_area;
  public Double get_community_area() {
    return community_area;
  }
  public void set_community_area(Double community_area) {
    this.community_area = community_area;
  }
  public crimes with_community_area(Double community_area) {
    this.community_area = community_area;
    return this;
  }
  private String fbi_code;
  public String get_fbi_code() {
    return fbi_code;
  }
  public void set_fbi_code(String fbi_code) {
    this.fbi_code = fbi_code;
  }
  public crimes with_fbi_code(String fbi_code) {
    this.fbi_code = fbi_code;
    return this;
  }
  private Double x_coordinate;
  public Double get_x_coordinate() {
    return x_coordinate;
  }
  public void set_x_coordinate(Double x_coordinate) {
    this.x_coordinate = x_coordinate;
  }
  public crimes with_x_coordinate(Double x_coordinate) {
    this.x_coordinate = x_coordinate;
    return this;
  }
  private Double y_coordinate;
  public Double get_y_coordinate() {
    return y_coordinate;
  }
  public void set_y_coordinate(Double y_coordinate) {
    this.y_coordinate = y_coordinate;
  }
  public crimes with_y_coordinate(Double y_coordinate) {
    this.y_coordinate = y_coordinate;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof crimes)) {
      return false;
    }
    crimes that = (crimes) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.date == null ? that.date == null : this.date.equals(that.date));
    equal = equal && (this.block == null ? that.block == null : this.block.equals(that.block));
    equal = equal && (this.primary_type == null ? that.primary_type == null : this.primary_type.equals(that.primary_type));
    equal = equal && (this.location_description == null ? that.location_description == null : this.location_description.equals(that.location_description));
    equal = equal && (this.arrest == null ? that.arrest == null : this.arrest.equals(that.arrest));
    equal = equal && (this.domestic == null ? that.domestic == null : this.domestic.equals(that.domestic));
    equal = equal && (this.beat == null ? that.beat == null : this.beat.equals(that.beat));
    equal = equal && (this.district == null ? that.district == null : this.district.equals(that.district));
    equal = equal && (this.ward == null ? that.ward == null : this.ward.equals(that.ward));
    equal = equal && (this.community_area == null ? that.community_area == null : this.community_area.equals(that.community_area));
    equal = equal && (this.fbi_code == null ? that.fbi_code == null : this.fbi_code.equals(that.fbi_code));
    equal = equal && (this.x_coordinate == null ? that.x_coordinate == null : this.x_coordinate.equals(that.x_coordinate));
    equal = equal && (this.y_coordinate == null ? that.y_coordinate == null : this.y_coordinate.equals(that.y_coordinate));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof crimes)) {
      return false;
    }
    crimes that = (crimes) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.date == null ? that.date == null : this.date.equals(that.date));
    equal = equal && (this.block == null ? that.block == null : this.block.equals(that.block));
    equal = equal && (this.primary_type == null ? that.primary_type == null : this.primary_type.equals(that.primary_type));
    equal = equal && (this.location_description == null ? that.location_description == null : this.location_description.equals(that.location_description));
    equal = equal && (this.arrest == null ? that.arrest == null : this.arrest.equals(that.arrest));
    equal = equal && (this.domestic == null ? that.domestic == null : this.domestic.equals(that.domestic));
    equal = equal && (this.beat == null ? that.beat == null : this.beat.equals(that.beat));
    equal = equal && (this.district == null ? that.district == null : this.district.equals(that.district));
    equal = equal && (this.ward == null ? that.ward == null : this.ward.equals(that.ward));
    equal = equal && (this.community_area == null ? that.community_area == null : this.community_area.equals(that.community_area));
    equal = equal && (this.fbi_code == null ? that.fbi_code == null : this.fbi_code.equals(that.fbi_code));
    equal = equal && (this.x_coordinate == null ? that.x_coordinate == null : this.x_coordinate.equals(that.x_coordinate));
    equal = equal && (this.y_coordinate == null ? that.y_coordinate == null : this.y_coordinate.equals(that.y_coordinate));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.date = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.block = JdbcWritableBridge.readString(3, __dbResults);
    this.primary_type = JdbcWritableBridge.readString(4, __dbResults);
    this.location_description = JdbcWritableBridge.readString(5, __dbResults);
    this.arrest = JdbcWritableBridge.readBoolean(6, __dbResults);
    this.domestic = JdbcWritableBridge.readBoolean(7, __dbResults);
    this.beat = JdbcWritableBridge.readInteger(8, __dbResults);
    this.district = JdbcWritableBridge.readInteger(9, __dbResults);
    this.ward = JdbcWritableBridge.readDouble(10, __dbResults);
    this.community_area = JdbcWritableBridge.readDouble(11, __dbResults);
    this.fbi_code = JdbcWritableBridge.readString(12, __dbResults);
    this.x_coordinate = JdbcWritableBridge.readDouble(13, __dbResults);
    this.y_coordinate = JdbcWritableBridge.readDouble(14, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.date = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.block = JdbcWritableBridge.readString(3, __dbResults);
    this.primary_type = JdbcWritableBridge.readString(4, __dbResults);
    this.location_description = JdbcWritableBridge.readString(5, __dbResults);
    this.arrest = JdbcWritableBridge.readBoolean(6, __dbResults);
    this.domestic = JdbcWritableBridge.readBoolean(7, __dbResults);
    this.beat = JdbcWritableBridge.readInteger(8, __dbResults);
    this.district = JdbcWritableBridge.readInteger(9, __dbResults);
    this.ward = JdbcWritableBridge.readDouble(10, __dbResults);
    this.community_area = JdbcWritableBridge.readDouble(11, __dbResults);
    this.fbi_code = JdbcWritableBridge.readString(12, __dbResults);
    this.x_coordinate = JdbcWritableBridge.readDouble(13, __dbResults);
    this.y_coordinate = JdbcWritableBridge.readDouble(14, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(date, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(block, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(primary_type, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(location_description, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBoolean(arrest, 6 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeBoolean(domestic, 7 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeInteger(beat, 8 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(district, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(ward, 10 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(community_area, 11 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeString(fbi_code, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDouble(x_coordinate, 13 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(y_coordinate, 14 + __off, 8, __dbStmt);
    return 14;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(date, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(block, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(primary_type, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(location_description, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBoolean(arrest, 6 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeBoolean(domestic, 7 + __off, -7, __dbStmt);
    JdbcWritableBridge.writeInteger(beat, 8 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(district, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(ward, 10 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(community_area, 11 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeString(fbi_code, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDouble(x_coordinate, 13 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(y_coordinate, 14 + __off, 8, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.date = null;
    } else {
    this.date = new Timestamp(__dataIn.readLong());
    this.date.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.block = null;
    } else {
    this.block = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.primary_type = null;
    } else {
    this.primary_type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.location_description = null;
    } else {
    this.location_description = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.arrest = null;
    } else {
    this.arrest = Boolean.valueOf(__dataIn.readBoolean());
    }
    if (__dataIn.readBoolean()) { 
        this.domestic = null;
    } else {
    this.domestic = Boolean.valueOf(__dataIn.readBoolean());
    }
    if (__dataIn.readBoolean()) { 
        this.beat = null;
    } else {
    this.beat = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.district = null;
    } else {
    this.district = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ward = null;
    } else {
    this.ward = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.community_area = null;
    } else {
    this.community_area = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.fbi_code = null;
    } else {
    this.fbi_code = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.x_coordinate = null;
    } else {
    this.x_coordinate = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.y_coordinate = null;
    } else {
    this.y_coordinate = Double.valueOf(__dataIn.readDouble());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id);
    }
    if (null == this.date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date.getTime());
    __dataOut.writeInt(this.date.getNanos());
    }
    if (null == this.block) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, block);
    }
    if (null == this.primary_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, primary_type);
    }
    if (null == this.location_description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, location_description);
    }
    if (null == this.arrest) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.arrest);
    }
    if (null == this.domestic) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.domestic);
    }
    if (null == this.beat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.beat);
    }
    if (null == this.district) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.district);
    }
    if (null == this.ward) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.ward);
    }
    if (null == this.community_area) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.community_area);
    }
    if (null == this.fbi_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, fbi_code);
    }
    if (null == this.x_coordinate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.x_coordinate);
    }
    if (null == this.y_coordinate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.y_coordinate);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id);
    }
    if (null == this.date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date.getTime());
    __dataOut.writeInt(this.date.getNanos());
    }
    if (null == this.block) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, block);
    }
    if (null == this.primary_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, primary_type);
    }
    if (null == this.location_description) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, location_description);
    }
    if (null == this.arrest) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.arrest);
    }
    if (null == this.domestic) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeBoolean(this.domestic);
    }
    if (null == this.beat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.beat);
    }
    if (null == this.district) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.district);
    }
    if (null == this.ward) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.ward);
    }
    if (null == this.community_area) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.community_area);
    }
    if (null == this.fbi_code) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, fbi_code);
    }
    if (null == this.x_coordinate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.x_coordinate);
    }
    if (null == this.y_coordinate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.y_coordinate);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date==null?"null":"" + date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(block==null?"null":block, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(primary_type==null?"null":primary_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(location_description==null?"null":location_description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(arrest==null?"null":"" + arrest, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(domestic==null?"null":"" + domestic, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(beat==null?"null":"" + beat, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(district==null?"null":"" + district, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ward==null?"null":"" + ward, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(community_area==null?"null":"" + community_area, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fbi_code==null?"null":fbi_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(x_coordinate==null?"null":"" + x_coordinate, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(y_coordinate==null?"null":"" + y_coordinate, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date==null?"null":"" + date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(block==null?"null":block, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(primary_type==null?"null":primary_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(location_description==null?"null":location_description, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(arrest==null?"null":"" + arrest, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(domestic==null?"null":"" + domestic, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(beat==null?"null":"" + beat, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(district==null?"null":"" + district, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ward==null?"null":"" + ward, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(community_area==null?"null":"" + community_area, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fbi_code==null?"null":fbi_code, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(x_coordinate==null?"null":"" + x_coordinate, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(y_coordinate==null?"null":"" + y_coordinate, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date = null; } else {
      this.date = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.block = null; } else {
      this.block = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.primary_type = null; } else {
      this.primary_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.location_description = null; } else {
      this.location_description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.arrest = null; } else {
      this.arrest = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.domestic = null; } else {
      this.domestic = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.beat = null; } else {
      this.beat = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.district = null; } else {
      this.district = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ward = null; } else {
      this.ward = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.community_area = null; } else {
      this.community_area = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.fbi_code = null; } else {
      this.fbi_code = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.x_coordinate = null; } else {
      this.x_coordinate = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.y_coordinate = null; } else {
      this.y_coordinate = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date = null; } else {
      this.date = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.block = null; } else {
      this.block = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.primary_type = null; } else {
      this.primary_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.location_description = null; } else {
      this.location_description = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.arrest = null; } else {
      this.arrest = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.domestic = null; } else {
      this.domestic = BooleanParser.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.beat = null; } else {
      this.beat = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.district = null; } else {
      this.district = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ward = null; } else {
      this.ward = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.community_area = null; } else {
      this.community_area = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.fbi_code = null; } else {
      this.fbi_code = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.x_coordinate = null; } else {
      this.x_coordinate = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.y_coordinate = null; } else {
      this.y_coordinate = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    crimes o = (crimes) super.clone();
    o.date = (o.date != null) ? (java.sql.Timestamp) o.date.clone() : null;
    return o;
  }

  public void clone0(crimes o) throws CloneNotSupportedException {
    o.date = (o.date != null) ? (java.sql.Timestamp) o.date.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("date", this.date);
    __sqoop$field_map.put("block", this.block);
    __sqoop$field_map.put("primary_type", this.primary_type);
    __sqoop$field_map.put("location_description", this.location_description);
    __sqoop$field_map.put("arrest", this.arrest);
    __sqoop$field_map.put("domestic", this.domestic);
    __sqoop$field_map.put("beat", this.beat);
    __sqoop$field_map.put("district", this.district);
    __sqoop$field_map.put("ward", this.ward);
    __sqoop$field_map.put("community_area", this.community_area);
    __sqoop$field_map.put("fbi_code", this.fbi_code);
    __sqoop$field_map.put("x_coordinate", this.x_coordinate);
    __sqoop$field_map.put("y_coordinate", this.y_coordinate);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("date", this.date);
    __sqoop$field_map.put("block", this.block);
    __sqoop$field_map.put("primary_type", this.primary_type);
    __sqoop$field_map.put("location_description", this.location_description);
    __sqoop$field_map.put("arrest", this.arrest);
    __sqoop$field_map.put("domestic", this.domestic);
    __sqoop$field_map.put("beat", this.beat);
    __sqoop$field_map.put("district", this.district);
    __sqoop$field_map.put("ward", this.ward);
    __sqoop$field_map.put("community_area", this.community_area);
    __sqoop$field_map.put("fbi_code", this.fbi_code);
    __sqoop$field_map.put("x_coordinate", this.x_coordinate);
    __sqoop$field_map.put("y_coordinate", this.y_coordinate);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
