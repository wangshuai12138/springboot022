

<table border="1" width="200px">
    <tr>
        <th> 编号 </th>
        <th> 姓名 </th>
        <th> 密码 </th>
    </tr>
    <#list userLIst as uList >
        <tr>
            <td> ${uList.id} </td>
            <td> ${uList.username} </td>
            <td> ${uList.password} </td>
        </tr>
    </#list>
</table>