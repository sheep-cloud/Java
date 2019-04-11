package cn.colg.collection;

import cn.colg.BaseTest;

/**
 * 集合测试
 * 
 * <pre>
 * 1. 存储对象可以考虑：数组；集合
 * 
 * 2. 数组存储对象的特点：Student[] stu = new Student[20]; stu[0] = new Student(); ...
 *    > 弊端: 
 *      1. 一旦创建，长度不可变；
 *      2. 真实的数组存放的对象的个数撒不可知的。
 *      
 * 3. 集合
 *    Collection 接口
 *          List 接口:    存储有序的，可以重复的元素
 *              ArrayList, LinkedList, Vector
 *          Set 接口:     存储无序的，不可重复的元素
 *              HashSet, LinkedHashSet, TreeSet
 *    Map 接口
 *          HashMap, LinkedHashMap, TreeMap, Hashtable（子类：Properties）
 * 
 * </pre>
 *
 * @author colg
 */
public class CollectionTest extends BaseTest {

}
