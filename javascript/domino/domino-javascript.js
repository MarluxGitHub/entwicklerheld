export const chain = (dominoes) => {
    const graph = {};
    const visited = new Array(dominoes.length).fill(false);
    const chain = [];

    // Build the graph
    for (let i = 0; i < dominoes.length; i++) {
      const [a, b] = dominoes[i];
      if (!graph[a]) graph[a] = [];
      if (!graph[b]) graph[b] = [];
      graph[a].push([b, i]);
      graph[b].push([a, i]);
    }

    // DFS to find a valid chain
    const dfs = (node) => {
      visited[node] = true;
      for (let i = 0; i < graph[node].length; i++) {
        const [nextNode, dominoIndex] = graph[node][i];
        if (!visited[nextNode]) {
          chain.push(dominoes[dominoIndex]);
          dfs(nextNode);
          if (chain.length === dominoes.length) return true;
          chain.pop();
        }
      }
      visited[node] = false;
      return false;
    };

    // Try starting the DFS from each node
    for (let i = 0; i < dominoes.length; i++) {
      chain.push(dominoes[i]);
      if (dfs(dominoes[i][0])) {
        return chain;
      }
      chain.pop();
    }

    return null;
  };