def y = 5
def x = (y > 1) ? "worked" : "failed"
assert x == "worked"

for(i in 0..9){
  x=x+i
 // println(x);
}

def a = null;  
// 如果a为“空”（null，空串""，[]，[:]），那么结果为?:之后的那个值; 如果不为“空”，那么结果就是a  
def result = a ?: "default result"  
println result  
  
a = "hello shake"  
result = a ?: "default result"  
println result  

def map = ['xxx':1,'yyy':2,'zzz':3]
println "map get 1 = " + map."yyy"
for(item in map) {
	println item.key +" " +item.value
}

map.each(){key,value-> println "${key} ------ ${value}"}


def stringList = [ "java", "perl", "python",
"ruby", "c#", "cobol",
"groovy", "jython",
"smalltalk", "prolog", "m", "yacc" ];

stringList.each(){
	item ->
	println "stringList item "+item
}

stringList.eachWithIndex(){
	obj,i ->
	println "${obj} at ${i}"
}

def shake(string){
	println "---------- " +string
}
shake("haha")

class Person {
	String name
	String age
}
class Twin {
	Person one, two
	def iterator(){
		println "iterator"
		return [one,two].iterator()
		}
}

def tw = new Twin(one:new Person(name:"shake"),two:new Person(name:"ask"))

shake(tw*.name)

class xClass
{
	def field
	def  getField(){
		++field 
	}
}
x  = new xClass()
x.field = 1
//调用的getField
shake(x.field)
//直接访问了field
shake(x.@field)

def user = null

def displayName = user?.name ? user.name :"Anonymous"

shake(displayName)

shake(15>>>1)

def transform(List list ,Closure action){
	def result=[]
	list.each{
		result << action(it)
	}
	result
}
def describe(Person p){
	"$p.name is $p.age"
}
def action = this.&describe
def list =[new Person(name:"shake",age:"18"),new Person(name:"ask",age:"20")]
println transform(list,action)

def doSomething(String str){
	str.toUpperCase()
}
def doSomething(int count){
	++count 
}

def reference = this.&doSomething
assert  reference("shake")=="SHAKE"
assert  reference(100) ==101
//正则表达式
def p110 = ~/match/ 
println p110.class

def text = "some text to match"
def m = text =~ /some/ 
m.find { obj ->
	println obj
}
//TODO ==~还有点问题
println text
def mx = text ==~ "some"
println mx

class Car {
	String make
	String mode
}
//适用与所有实现iterator接口的类
def cars = [new Car(make:"China",mode:"BYD"),null,new Car(make:"Jepan",mode:"BYD")]
println cars*.make

class Component{
	Long id 
	String name
}

class CompositeObject implements Iterable<Component>{

	def components =[ 
		new Component(id:1, name: 'Foo'),
        new Component(id:2, name:'Bar')]
	@Override
	Iterator<Component> iterator(){
		components.iterator()
	}
}
def compositeObject = new CompositeObject();
println  compositeObject*.name

int addFunction(int x,int y,int z){
	x+y+z
}

def counts = [1,2,3]
assert addFunction(*counts) ==6
counts =[2,3]
assert addFunction(*counts,10) ==15

def countItems = [1,*counts]
assert countItems == [1,2,3]

def m1 = [c:3,d:4]
def m2 = [a:1,b:2,*:m1]
assert m2==[a:1, b:2, c:3, d:4]
m2 = [a:1,b:2,*:m1,d:5]
assert m2==[a:1, b:2, c:3, d:5]

def range= (0..5)

println range.collect()
println (0..<5).collect()
println range.size()

//比较 <=> compareTo
assert 1<=>1 ==0
assert 1<=>2 ==-1
assert 2<=>1 ==1
assert 'a'<=>'c' ==-1


//调用getAt 和putAt
def list1 = [1,2,3,4]
assert list1[1] ==2
list1[0]=2
assert list1[0] ==2
list1[0..1] =[8,3]
println list1 
class User{
	Long id 
	String name
	def getAt(int i){
		switch(i){
			case 0: return id;
			case 1: return name;
		}
		throw new IllegalArgumentException("No such element $i")
	}
	void putAt(int i,def value){
		switch(i){
			case 0:id = value 
			return
			case 1:name =value
			return
		}
		throw new IllegalArgumentException("No such element $i")
	}
	def getName(){
		"ss "
	}
}
user = new User(id:1,name:"shake")
assert user[0] == 1
assert user[1] =="shake"
user[1] ="ask"
println user.@name

// in contains isCase
list1 = ["shake","ask"]
assert ("shake" in list1)
assert (list1.contains("shake"))
assert (list1.isCase("shake"))

// == 就是java中的 equest ; is 就是java中的==
def list3 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']        
def list2 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']        
assert list3 == list2                                       
assert !list3.is(list2)

// as直接可以指定是什么类型
class ClassName{
	String name
}
class ClassTwo{
	String name
	String age
	def asType(Class target){
		if (target == ClassName) {
			return new ClassName(name:name)
		}
		throw ClassCaseException("class case exception")
	}	
}
def  ctwo = new ClassTwo(name:"shake",age:"19")
assert ctwo instanceof ClassTwo
def cnOne = ctwo as ClassName
assert cnOne instanceof ClassName
println cnOne.name

class MyCallable{
	int call(int x){
		x*2
	}
}
def mycall = new MyCallable()
assert mycall(2)==4
assert mycall.call(3) ==6

trait FlyingAbility{
	String fly(){
		"I'm flying"
	}
}
class Bird implements FlyingAbility{}
def b = new Bird()
println b.fly()

trait Greetable{
	abstract def name()
	def greet(){
		"Hello ${name()}"
	}
}
class GreetableImpl implements Greetable{
	String name(){
		"shake"
	}
}
def gg = new GreetableImpl()
println gg.greet()

trait Introspector {
    String name ="shake822"
    Introspector whoAmI() { this }
}
class Foo implements Introspector {}
def foo = new Foo()
println foo.whoAmI().is(foo)
println foo.name

trait Counter{
	private int count =0
	int count(){
		count=count +1
		count
	}
}
class CounterClass implements Counter{}
def cc = new CounterClass()
assert cc.count() ==1

trait Named {
    public String name                      
}
class Person2 implements Named {}            
def p = new Person2()                        
p.Named__name = 'Bob' 
println p.Named__name

trait WithId {                                      
    Long id
}
trait WithName {                                    
    String name
}
trait Identified implements WithId, WithName {} 

trait DynamicObject{
	private Map props = [:]
	def methodMissing(String name ,args){
		name.toUpperCase()
	}
	def propertyMissing(String prop){
		props["${prop}"]
	}
	void setProperty(String prop,Object value){
		props["${prop}"] =value
	}
}
class Dynamic implements DynamicObject{
	String existsingProperty='property ok'
	String existingMethod(){
		"method ok"
	}
}
def dd = new Dynamic()
println dd.pp
dd.pp="xxx"
println dd.pp
println dd.sdfsdfsdf()
dd.xxx="dds"
println dd.xxx

trait A {
    String exec() { 'A' }               
}
trait B {
    String exec() { 'B' }               
}
class C implements B,A{} 
def c = new C()
assert c.exec() == 'A'
class D implements B,A{
	String exec(){
		B.super.exec()
	}
}
def d3 = new D()
assert d3.exec() =='B'


trait Extra {
    String extra() { "I'm an extra method" }        

}
class Something {                                       
    String doSomething() { 'Something' }                
}
def s = new Something() as Extra
println s.extra()
def n = new Something()
def m3= n.withTraits Extra
println m3.extra()
//Chaining behavior

interface MessageHandler {
    void on(String message, Map payload)
}
trait DefaultHandler implements MessageHandler{
	void on(String message,Map payload){
		println "DefaultHandler  ___  $message" 
		 super.on(message, payload)   
	}
}
trait OtherHandler implements MessageHandler{      
    void on(String message ,Map payload){
    	println " in Extra  $message"
    }    
}
trait LoggingHandler implements MessageHandler {                            
    void on(String message, Map payload) {
        println "Seeing $message with payload $payload"                     
        super.on(message, payload)                                          
    }
}

class HandlerWithLog implements OtherHandler, DefaultHandler,LoggingHandler{}
def loggingHandler = new HandlerWithLog()
loggingHandler.on("test hello shake ",[:])

trait Filtering{
	StringBuilder append(String str){
		def newStr = str.replace('o','')
		super.append(newStr)
	}
	String toString(){
		println "Filtering toString"
		super.toString()
	}
}
def sb = new StringBuilder().withTraits Filtering
sb.append("oop")
println sb

trait Greeter {
    String greet() { "Hello ${name} $name ${getName()} " }        
    abstract String getName()      
}
Greeter greeter = { "shake" }  
println greeter.greet()

class OtherGreeter{
	 String greet() { "OtherGreeter " }    
}
class GreeterTest extends OtherGreeter  implements Greeter{
	 
	String getName(){
		"ask"
	}
}

def greetertest = new GreeterTest();
println greetertest.greet()

class ClassA {
	String medthodFromA(){
		"A"
	}
}

class ClassB{
	String medthodFromB(){
		"B"
	}
}
ClassA.metaClass.mixin ClassB
def aa = new ClassA()

println aa.medthodFromB()

trait TestHelper{
	public static boolean CALLED = false
	public static init(){
		println "TestHelper init called"
		CALLED = true
	}
}
class Foo1 implements TestHelper{}
class Bar implements TestHelper {}              
class Baz implements TestHelper {}              
Bar.init()                                      
assert Bar.TestHelper__CALLED                   
assert !Baz.TestHelper__CALLED

Foo1.init()
println Foo1.TestHelper__CALLED

trait IntCouple{
	int x =1
	int y =2
	int sum(){
		//x+y ==3
		getX()+getY()
	}
}

class IntCoupleImpl implements IntCouple{
	int x =3
	int y=4
	int f(){
		sum()
	}
}
def intTest = new IntCoupleImpl()
assert intTest.f() ==7

  x = new java.util.Date()
println x
println "    hello&shake ".split('&')

def (bb,bn,bm) = [10,20,40]
assert bb==10

class Coordinates {
    double latitude
    double longitude

    double getAt(int idx) {
        if (idx == 0) latitude
        else if (idx == 1) longitude
        else throw new Exception("Wrong coordinate index, use 0 or 1")
    }
}
def coordinates = new Coordinates(latitude: 43.23, longitude: 3.67) 

def (la, lo ) = coordinates                                          

assert la == 43.23                                                  
assert lo == 3.67
def xx = "bar" 

switch ( xx ) {
    case "foo":
        result = "found foo"
        // lets fall through

    case "bar":
        result += "bar"

    case [4, 5, 6, 'inList']:
        result = "list"
        break

    case 12..30:
        result = "range"
        break

    case Integer:
        result = "integer"
        break

    case Number:
        result = "number"
        break

    case ~/fo*/: // toString() representation of x matches the pattern?
        result = "foo regex"
        break

    case { it < 0 }: // or { x < 0 }
        result = "negative"
        break

    default:
        result = "default"
}

assert result == "list"

interface Predicate<T> {
    boolean accept(T obj)
    boolean ssss(T obj)
}
Predicate filter = [accept:{it.contains 'G' },ssss:{it.contains 'XX'}] as Predicate
assert filter.ssss('Groovy') == false
assert filter.accept("GG") ==true

@groovy.transform.TypeChecked
class Person5 {                                                          
    String firstName
    String lastName
}
Person5.metaClass.getFormattedName = {"$delegate.firstName $delegate.lastName"}
def p5 = new Person5(firstName: 'Raymond', lastName: 'Devos')             

assert p5.formattedName == 'Raymond Devos'                             
class Duck1 {
    void quack() {              
        println 'Quack1!'
    }
}
class QuackingBird {
    void quack() {              
        println 'Quack2!'
    }
}
//@groovy.transform.TypeChecked
void accept(quacker) {
    quacker.quack()             
}
accept(new Duck1())
//The method call works because 

//@groovy.transform.TypeChecked 在编译时期检查类型匹配
 @groovy.transform.TypeChecked
void flowTyping() {
    def o = 'foo'               
     o = o.toUpperCase()            
    o = 9d 
    o = Math.sqrt(o)                    
} 

@groovy.transform.TypeChecked
void flowTypingWithExplicitType(){
	List list =['a','b','c'] 
	list = list*.toUpperCase() 
	//list.add(1)
	println list
}
this.&flowTypingWithExplicitType()

int compute(String string) { string.length() }
String compute(Object o) { "Nope" }
Object o = 'string'
def result1 = compute(o)
println result1

class Top {
   void methodFromTop() {}
}
class Bottom extends Top {
   void methodFromBottom() {}
}
def ox = new Top()                               
Thread.start {
    ox = new Bottom()                            
}
ox.methodFromTop()                               
//ox.methodFromBottom()  // compilation error 

@groovy.transform.TypeChecked
int testClosureReturnTypeInference(String arg) {
    def cl = { "Arg: $arg" }           
    println cl()                 
    def val = cl()                                          
    val.length()                                            
}
this.&testClosureReturnTypeInference("shake")

//compute 必须是String类型
@groovy.transform.TypeChecked
class AA {
    String compute() { 'some string' }             
    def computeFully() {
        compute().toUpperCase()                 
    }
}
//

class PersonAA {
    String name
    int age
}

void inviteIf(PersonAA p, Closure<Boolean> predicate) {           
    if (predicate.call(p)) {
    	 println p.age
        // send invite
        // ...
    }
}

@groovy.transform.TypeChecked
void failCompilation() {
    PersonAA p = new PersonAA(name: 'Gerard', age: 55)
    inviteIf(p) {PersonAA it ->                                        
        it.age >= 18 // No such property: age                   
    }
}
this.&failCompilation()

class Computer {
    int compute(String str) {
        str.length()
    }
    String compute(int x) {
        String.valueOf(x)
    }
}
//CompileStatic后续的改变无反应
@groovy.transform.CompileStatic
void test() {
    def computer = new Computer()
    computer.with {
        assert compute(compute('foobar')) =='6'
    }
}

@groovy.transform.TypeChecked
void test1(){
	def computer = new Computer()
	computer.with {
		println compute("shake")
	}
}
Computer.metaClass.compute={String str -> new Date()}
def computer = new Computer() 
this.&test()
this.&test1()