#include <mutex>
#include <chrono>
#include <thread>
#include <iostream>

#define BUFFER_SIZE 100

int count = 0;

std::mutex mtx;

void produce_item(){
    return;
}

void consume_item(){
    return;
}

void enter_item(){
    return;
}

void remove_item(){
    return;
}

void producer(){
    std::cout << "producer entrou!\n";
    while (true)
    {
        produce_item();
        if(count == BUFFER_SIZE){
            std::cout << "Fila cheia! producer parando de produzir...\n";
            do{
                std::this_thread::sleep_for(std::chrono::milliseconds(10));
            }
            while(count>1);
        }
        if(!mtx.try_lock()) continue;
        enter_item();
        count+=1;
        std::cout << count << std::endl;
        mtx.unlock();
    }
}

void consumer(){
    std::cout << "consumer entrou!\n";
    while (true)
    {
        if(count == 0){
            std::cout << "Fila vazia! consumer parando de consumir...\n";
            do{
                std::this_thread::sleep_for(std::chrono::milliseconds(10));
            }
            while(count<BUFFER_SIZE-1);
        }
        if(!mtx.try_lock()) continue;
        remove_item();
        count-=1;
        std::cout << count << std::endl;
        mtx.unlock();
    }
}



int main(){
    std::thread t1(consumer);
    std::thread t2(producer);

    t1.join();
    t2.join();
}