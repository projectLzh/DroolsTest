[when][]小于或等于=<=
[when][]是===
[when]学生办找一个人=$p:Person()
[when][]名字=name
[when][]年龄=age
[when][]学名=name
[when][]- {operator} {value:\d*}={operator} {value}
[then]学习决定将你安排到"{className}"=$p.setClassName("{className}");
[then]打印出 {names:[_a-zA-Z0-9^"]+}=System.out.println("{names}");
