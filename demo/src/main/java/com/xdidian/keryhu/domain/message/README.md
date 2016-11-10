此package 最主要的服务于 ：

   新地点的message组件。也就是消息提醒的组件。
   
   其中subject enum class 目的：
   
   创建一个有限的集合，包含像：待审核的公司，新同事加入等关键字，显示在前台方便，统计哪个组题有新的消息了
   
   operate  enum class 是一个加减操作，就是add，minus（减少），
     目的： 让message统计每一个主题的计数，增1，或减1，当有新的主题出现，该主题就+1，如果该主题有一个被读
     
     
   messageCommunicateDto
   让其他组件和message交互，通知他哪个组件需要加，还是减
    由subject ,userId(如果此message是针对新地点的，那么userId可以为null，否则必填）
    ReadGroup， operate组成，这个也是全局的
    
    针对message的操作，查询message数据库，首先根据messageCommunicateDto 的ReadGroup来判断，
    查询新地点，还是查询个人，
    如果查询新地点，那么从数据库ReadGroup==新地点的找到一条记录，然后找到包含，subject和count 的list，
    将具体的增减操作做进去。