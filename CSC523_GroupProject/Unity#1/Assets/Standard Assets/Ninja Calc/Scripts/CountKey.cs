using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CountKey : MonoBehaviour
{

    public int keycount = 0;
    List<GameObject> childrens = new List<GameObject>();
    void Start()
    {

        while (keycount < gameObject.transform.childCount)
        {
            childrens.Add(gameObject.transform.GetChild(keycount).gameObject);
            keycount++;
        }
        Debug.Log(keycount);
    }

}
