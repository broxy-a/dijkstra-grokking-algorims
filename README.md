# Алгоритм Дейкстры

Этот код реализует алгоритм Дейкстры для поиска кратчайшего пути в графе от точки `start` до точки `end`. Проект создан в рамках изучения книги **"Грокаем алгоритмы"** (Aditya Bhargava).

## Описание
Алгоритм Дейкстры используется для нахождения кратчайшего пути в графе с неотрицательными весами ребер. В данном примере граф представлен в виде хэш-таблиц, где:
- `graph` описывает узлы и их соседей с весами.
- `cost` хранит текущие стоимости достижения каждого узла.
- `parents` хранит информацию о родителях узлов для восстановления пути.

# Dijkstra's Algorithm

This code implements Dijkstra's algorithm to find the shortest path in a graph from the `start` node to the `end` node. The project was created as part of studying the book **"Grokking Algorithms"** by Aditya Bhargava.

## Description
Dijkstra's algorithm is used to find the shortest path in a graph with non-negative edge weights. In this example, the graph is represented using hash maps, where:
- `graph` describes nodes and their neighbors with weights.
- `cost` stores the current costs to reach each node.
- `parents` stores information about the parent nodes to reconstruct the path.
