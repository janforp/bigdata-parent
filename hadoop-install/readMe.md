# 安装：
## http://blog.csdn.net/dream_an/article/details/52946840

# 慕课视频
## http://www.imooc.com/course/list?c=%E5%A4%A7%E6%95%B0%E6%8D%AE

#1:Hadoop是什么？
http://os.51cto.com/art/201207/346023.htm

#2:Hadoop核心机制详细解析
http://os.51cto.com/art/201207/345808.htm
Hadoop的核心机制是通过HDFS文件系统和MapReduce算法进行存储资源、内存和程序的有效利用与管理。在现实的实例中，通过Hadoop，可以轻易的将多台普通的或低性能的服务器组合成分布式的运算-存储集群，提供大数据量的存储和处理能力。
知其然，知其所以然。要想深入学习和理解Hadoop的核心机制，还要从MapReduce和HDFS的原理入手。
MapReduce的“大事化小”
作为Google提出的架构，MapReduce通过Map（映射）和Reduce（化简）来实现大规模数据（TB级）的并行计算。可以简单理解为，通过Map（映射）函数，把一组键值对映射成一组新的键值对；指定并发的Reduce（化简）函数，用来保证所有映射的键值对中的每一个共享相同的键组。
MapReduce是一种大数据计算的开发模式和思想方法。开发人员先分析需求所提出问题的解决流程，找出数据可以并发处理的部分（Reduce），也就是那些能够分解为小段的可并行处理的数据，再将这些能够采用并发处理的需求写成Map程序（Map）。
然后就可以使用大量服务器来执行Map程序，并将待处理的庞大数据切割成很多的小份数据，由每台服务器分别执行Map程序来处理分配到的那一小段数据，接着再将每一个Map程序分析出来的结果，透过Reduce程序进行合并，最后则汇整出完整的结果。
MapReduce的整个流程就像…
MapReduce是Hadoop分布式计算的关键技术，将要执行的问题，拆解成Map和Reduce的方式来执行，以达到分散运算的效果。例如要搜寻网页中的“In Big Data”这个词，可以先用Map程序，来计算出所有网页中，每一个词的位置。再使用Reduce程序，在每一个字的清单中，检索出“In Big Data”所对应的URL，您就来到了这个博客。MapReduce程序的执行过程如下：

