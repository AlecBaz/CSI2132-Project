import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function ChainComponent() {
  const { chainId } = useParams(); // ✅ grabs chainId from the URL
  const [chain, setChain] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/api/chains/${chainId}`)
      .then((res) => res.json())
      .then((data) => setChain(data))
      .catch((err) => console.error(err));
  }, [chainId]);

  return (
    <div>
      {chain ? (
        <div>
          <h2>{chain.chainName}</h2>
          <p>Email: {chain.chainAddress}</p>
        </div>
      ) : (
        <p>Loading chain...</p>
      )}
    </div>
  );
}

export default ChainComponent;
