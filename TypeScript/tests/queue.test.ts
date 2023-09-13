import { newArrayIntQueue } from "../src/arrayqueue";
import { newLinkedListIntQueue } from "../src/linkedlistqueue.js";

// pick one queue implementation, can run test easily for both, due to subtype polymorphism
let createQueue = newLinkedListIntQueue
// let createQueue = newArrayIntQueue

// TODOs:
// write more test cases to test dequeue and clear functions.

test("test isEmpty: newly created list should be empty", () => {
    expect(createQueue().isEmpty()).toBeTruthy()
})

test("test isEmpty: list with 1 element is not empty", () => {
    const queue = createQueue()
    queue.enqueue(2)
    expect(queue.isEmpty()).toBeFalsy()
})

test("test peek: newly created list should peek null", () => {

    expect(createQueue().peek()).toBeNull()
})

test("test peek: queue with 2 element should peek the one that was most recently added", () => {
    const queue = createQueue()
    queue.enqueue(2)
    queue.enqueue(3)
    expect(queue.peek()).toEqual(3)
})

let param = [5, 10, 1000000]
// parameterized test, apply to each value of the parameter
test.each(param)("test enqueue: enqueued number %d is correct", (nr) => {
    const queue = createQueue()
    queue.enqueue(nr)
    expect(queue.peek()).toBe(nr)
})

// can nest tests with shared descriptions for better readability
describe("test size: ", ()=> {
    test("1 entry", ()=>{
        const queue = createQueue()
        queue.enqueue(5)
        expect(queue.size()).toBe(1)
    })

    test("11 entries", ()=>{
        const queue = createQueue()
        for (let i =0;i<11;i++)
            queue.enqueue(i)
        expect(queue.size()).toBe(11)
    })
})

test("test dequeue: dequeue from an empty queue should return null", () => {
    const queue = createQueue()
    expect(queue.dequeue()).toBeNull()
})

test("test dequeue: dequeue after enqueue should return the enqueued value", () => {
    const queue = createQueue()
    queue.enqueue(5)
    expect(queue.dequeue()).toBe(5)
})

test("test dequeue: multiple enqueues and dequeues should maintain order", () => {
    const queue = createQueue()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    expect(queue.dequeue()).toBe(3)
    expect(queue.dequeue()).toBe(2)
    expect(queue.dequeue()).toBe(1)
})

test("test clear: clearing the queue should make it empty", () => {
    const queue = createQueue()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.clear()
    expect(queue.isEmpty()).toBeTruthy()
})

test("test clear: clearing the queue should reset its size to 0", () => {
    const queue = createQueue()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.clear()
    expect(queue.size()).toBe(0)
})

test("test peek: after dequeue, peek should return the next element", () => {
    const queue = createQueue()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.dequeue()
    expect(queue.peek()).toBe(1)
})

test("test size: after dequeue, size should decrease by 1", () => {
    const queue = createQueue()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.dequeue()
    expect(queue.size()).toBe(1)
})
