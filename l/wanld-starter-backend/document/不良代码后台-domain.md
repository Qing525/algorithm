

#### [2019/9/24] 实体类缺少注释，字段缺少注释，缺少@Column注解：
``` java
public class MeetingRecordList extends BaseEntity {
    private String title;
    private String content;
    private Long meetingRecordId;
    private Byte isDeleted;
}
```
#### [2019/9/24] 定义软删除的实体必须按如下方式定义字段：

``` java
@Column(name = "is_deleted", nullable = false)
@ColumnDefault("0")
Boolean isDeleted = false;
```
 