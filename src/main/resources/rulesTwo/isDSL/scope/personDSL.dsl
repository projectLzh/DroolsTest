[keyword]规则开始=rule
[keyword]规则结束=end
[when]小于或等于=<=
[when]是===
[when]年龄=age
[when]名字=name
[when][]- {field:\w*} ={field}
[when]学生办找一个人=$p:Person()
[then]名字=System.out.println($p.getName());
[then]学习决定将你安排到"{className}"=$p.setClassName("{className}");
