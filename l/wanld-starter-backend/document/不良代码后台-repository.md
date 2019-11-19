
#### [2019/9/24] 未格式化，方法可简化为findByRoleId:
``` java
public interface RolePermissionRelationRepository extends JpaRepository<RolePermissionRelation,Long> {
...
List<RolePermissionRelation> findWldRolePermissionRelationsByRoleId(Long roleId);
}
```

#### [2019/9/24] 返回类型禁止用Object:
```java
@Query(...)
List<Map<String,Object>> findAllMeetingRoom();
```

#### 单词拼写错误，MeetingRoom应为Meetingroom：
``` java
public class MeetingRoom extends AbstractSoftDeleted {
    ...
}
```