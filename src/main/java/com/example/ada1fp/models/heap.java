package com.example.ada1fp.models;

import java.util.ArrayList;

public class heap {
    private final ArrayList<task> heap;

    public heap() {
        this.heap = new ArrayList<>();
    }

    // Insertar un elemento en el heap
    public void insert(task value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Obtener el máximo (raíz del heap)
    public task getMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // Eliminar el máximo
    public task extractMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        task max = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        if (!heap.isEmpty()) {
            heapifyDown(0);
        }
        return max;
    }

    // Heapify hacia arriba
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).getPriority() > heap.get(parentIndex).getPriority()) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Heapify hacia abajo
    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && heap.get(leftChild).getPriority() > heap.get(largest).getPriority()) {
                largest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).getPriority() > heap.get(largest).getPriority()) {
                largest = rightChild;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // Obtener el arreglo completo del heap
    public ArrayList<task> getHeap() {
        return new ArrayList<>(heap); // Devuelve una copia para evitar modificaciones externas
    }

    // Incrementar la clave de un elemento en el heap
    public void increaseKey(int index, int newPriority) {
        if (newPriority < heap.get(index).getPriority()) {
            throw new IllegalArgumentException("New priority must be greater than or equal to the current priority");
        }
        heap.get(index).setPriority(newPriority);
        heapifyUp(index);
    }

    // Intercambiar dos elementos en el array
    private void swap(int i, int j) {
        task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Obtener el tamaño del heap
    public int size() {
        return heap.size();
    }

    // Verificar si el heap está vacío
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void maintainHeap() {
        // Recorre el heap desde el último elemento hacia arriba para aplicar heapifyUp
        for (int i = heap.size() - 1; i >= 0; i--) {
            heapifyUp(i);
        }
        // Recorre el heap desde la raíz hacia abajo para aplicar heapifyDown
        for (int i = 0; i < heap.size(); i++) {
            heapifyDown(i);
        }
    }
}