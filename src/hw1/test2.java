package hw1;

public class test2 {
	
	public static void main(String args[]){
		VipQueue<Integer> vq = new VipQueue<Integer>(10);
		for (int i=0; i<5; i++){
		if (!vq.isFull()) vq.enqueue((Integer)i); //a "regular" enqueue
		if (!vq.isFull()) vq.vipEnqueue((Integer)(i*i)); //a vip enqueue
		}
		while (!vq.isEmpty()) System.out.printf("->%d", vq.dequeue());
		}

}
