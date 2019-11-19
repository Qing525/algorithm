
#### [2019/9/24] 在for循环数据查询； findWldPermissionById 可用findById取代 
```java
@Override
public List<Permission> getPermissionList(Long roleId) {
    List<Permission> permissions = new ArrayList<>();
    List<RolePermissionRelation> rolePermissionRelationList = rolePermissionRelationRepository.findWldRolePermissionRelationsByRoleId(roleId);
    if (!rolePermissionRelationList.isEmpty()) {
        for (RolePermissionRelation relation : rolePermissionRelationList) {
            permissions.add(permissionRepository.findWldPermissionById(relation.getPermissionId()));
        }
    }
    return permissions;
}
```
