# Index

* [Base](#base)
  * [문제 유형 파악](#문제-유형-파악)
  * [입출력의 크기와 범위 확인](#입출력의-크기와-범위-확인)
* 완전 탐색
  * [BFS](#bfs)
  * [DFS](#dfs), [백트래킹](#백트래킹)
  * [Memoization](#memoization)
  * [순열, 조합](#순열,-조합)
  * [이분 탐색](#이분-탐색)
* 그래프
  * [MST](#mst)
    * [Kruskal](#kruskal)
    * [Prim](#prim)
  * [Dijkstra](#dijkstra)
  * [Bellman Ford](#bellman-ford)
  * [Floyd Warshall](#floyd-warshall)
* 문자열
  * [KMP](#kmp)
* [DP](#dp)
* [위상정렬](#위상정렬)
* [시뮬레이션](#시뮬레이션)
* [그리디](#그리디)
* [기타](#기타)



## Base

* 기본적으로 코드의 시간복잡도를 대충 계산해 볼 줄 알면 편함
  
* 무조건 최악의 경우를 기준으로 계산(범위 최대, 경우의 수 최대 등)
  
* ### 문제 유형 파악

  * 무슨 문젠지 파악 후 [확인](#입출력의-크기와-범위-확인)

  * 기본적으로 해볼 수 있는 건 [완전 탐색](#완전-탐색) (다 해보기)

    * 시간 줄이기
      * 메모리 <-> 시간 TradeOff
      * 공간이 충분하다면 한 번에 참조할 수 있도록 저장하여 탐색할 필요가 없게 만들기
      * 불필요한 연산 가지치기 (이분탐색 등)
    
  * 문제 속 힌트

    * 무방향 그래프 & 최소 비용으로 모든 노드를 연결 - [MST](#MST)
    * 방향 그래프
      * 한 지점에서 각 지점까지의 최단 거리 - [Dijkstra](#dijkstra)
      * 음의 가중치를 가진 그래프에서 한 지점에서 각 지점까지의 최단 거리 - [Bellman Ford](#bellman-ford)
      * 각 지점에서 모든 지점까지의 최단 거리 - [Floyd Warshall](#floyd-warshall)
    * 여러 우열 관계를 통해 전체 순위 만들기 - [위상정렬](#위상정렬)

    * 정렬되어 있는 리스트가 주어진다 - [이분 탐색](#이분-탐색)을 생각해 볼 수 있음
    * 어떤 map(r*c)이 주어진다 - 탐색 문제 ([BFS](#bfs), [DFS](#dfs))
    * LIS, LCS - [DP](#dp)
    * 탐색해야 하는 문제에서 어떤 조건의 최대값이 12 이하로 나올 때 - [순열, 조합](#순열,-조합)
      * ex) 치킨배달 문제에서 치킨집의 개수
    * 생각 나면 추가




* ### 입출력의 크기와 범위 확인

  * BufferedReader / BufferedWriter

    * 그냥 항상 Scanner 대신 쓰면 편한데 귀찮다면 10만 단위가 넘어갈 만큼 많을 때 사용

    * 예를 들어, N\*N을 탐색하면서 M개를 고려해야 한다 

      => N\*N\*M의 결과가 문제의 시간 제한을 넘을 것 같다면 줄여야 하는 문제

      * 보통 1억~1억4천만번의 연산에 1초가 소요된다고 생각

  * 시간 복잡도 줄이기

  * O(1)로 풀어야 하는 문제는 보통 수학적인 거

  

## 완전 탐색

* ### BFS

  * Queue를 이용
  * 다른 조건을 최소화하는 문제 - PriorityQueue를 이용
  * 최소를 찾는 문제에서 BFS를 사용할 경우, 꺼내어 보는 각 step이 단조증가를 이루어 종료 조건에 처음 도달했을 때 최소를 찾을 수 있어야 함. 
    * ex) 큐 안에 들어오는 값이 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4... 와 같이 증가하는 형태여야 함
  * 중복제거
    * 문제에서 주어지는 각 조건을 배열의 차원으로 사용
      * ex) r * c, 열쇠 10가지 => check\[r]\[c]\[keys] 3차원

* ### DFS

  * Stack 또는 재귀함수 이용
  * 더 이상 진행할 필요가 없는 경우 진행하지 않으면 백트래킹

* ### 백트래킹

  * 중복제거
    * 문제에서 주어지는 각 조건을 배열의 차원으로 사용
      * ex) r * c, 열쇠 10가지 => check\[r]\[c]\[keys] 3차원

* ### Memoization

  * BFS, DFS에서의 중복 제거와 같이 memo 배열의 각 차원은 문제에서 제시된 각 조건과 매칭되어야 함
    * 각 조건들의 조합으로 생기는 경우의 수들 각각을 저장하기 때문
  * 이미 시도했던 경우의 수를 다시 연산하는 것을 방지

* ### 순열, 조합

  * for문과 재귀를 이용해 nPIr (중복 순열 - base 수열로 만들어질 수 있는 모든 경우의 수열)을 생성
  * nPIr에서 증가하는 수열만 골라내면 - 조합
  * nPIr에서 같거나 증가하는 수열만 골라내면 - 중복조합
  * nPIr에서 수가 중복되지 않도록 하면 - 순열

* ### 이분탐색

  * 정렬된 List에서만 사용 가능

    

## 그래프

* ### MST

  * 무방향 그래프에서 모든 정점을 최소비용으로 연결하는 알고리즘

  * #### Kruskal

    * ElogE
    * 모든 간선을 PriorityQueue에 add하고 시작
    * 가장 짧은 간선부터 연결
      * [Union Find](#union-find) 사용

  * #### Prim

    * ElogV
    * 현재 정점에 연결된 간선들만 PriorityQueue에 add
    * 가장 짧은 간선을 통해 이동한 정점에서 add 반복
    * 간선의 개수가 매우 많은 경우 사용

* ### 최단 거리 알고리즘

  * 방향 그래프에서 정점 사이의 최단 거리를 구하는 알고리즘

  * #### Dijkstra

    * 한 정점에서 각 정점 사이의 최단 거리

  * #### Bellman Ford

    * 음의 가중치가 존재하는 그래프에서의 다익스트라

  * #### Floyd Warshall

    * 각 정점에서 각 정점 사이의 모든 최단 거리