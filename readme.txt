
Using annotation instead of xml to configure all mybatis file

using UserMapper.class to replace UserMapper.xml
and that class is an interface
annotation @select @update @insert @delete @selectKey to rewrite what we do in demo01

how to call the interface
Mapper mapper = session.getMapper(mapperclass)
using that mapper to call method we defined.


need to include this usermapper to mybatis configuration root file

no need to use &gt; &lt; in java class we can simply use < >  can refer to selectUserByBirthdayRange