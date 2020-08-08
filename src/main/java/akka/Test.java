package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author: Created by zhanghl
 * @Date: 2020/4/22
 */
public class Test {
		/*public static void main(String[] args) {
				ActorSystem system = ActorSystem.create("hello");
				ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWord");
				System.out.println("Test:------------");
				System.out.println(a.path());
				system.shutdown();
		}*/
		public static void main(String[] args) {
				akka.Main.main(new String[] { HelloWorld.class.getName() });
		}
}

class HelloWorld extends UntypedActor{
		@Override
		public void onReceive(Object msg) throws Exception {
				if(msg == Greeter.Msg.DONE){
						System.out.println("end the talking ");
						getContext().stop(getSelf());
				}else{
						unhandled(msg);
				}
		}

		@Override
		public void preStart() throws Exception {
				super.preStart();
				final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class));
				greeter.tell(Greeter.Msg.CREET, getSelf());
		}
}

class Greeter extends UntypedActor{
		public enum Msg{
				/**
				* @Description: 表示开始通信
				*/
				CREET(0),
				/**
				* @Description: 表示通信结束
				*/
				DONE(1);
				private int value;
				private Msg(int value){
						this.value = value;
				}
				public int getValue(){
						return value;
				}
		}

		@Override
		public void onReceive(Object msg) throws Exception {
				if(msg == Msg.CREET){
						System.out.println("hello, world");
						Thread.sleep(2000);
						getSender().tell(Msg.DONE, getSender());
				}else{
						unhandled(msg);
				}
		}
}


