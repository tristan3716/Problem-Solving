let fs = require('fs');
let input = fs.readFileSync('/dev/stdin').toString().split('\n');

const find = (list, x) => {
    if (list[x].parent === list[x].id) {
        return list[x].id;
    } else {
        list[x].parent = find(list, list[x].parent);

        list[x].truth = list[x].truth || list[list[x].parent].truth;
        list[list[x].parent].truth = list[x].truth || list[list[x].parent].truth;

        return list[x].parent;
    }
}

const union = (list, a, b) => {
    const pa = find(list, a);
    const pb = find(list, b);

    if (list[pa].parent !== list[pb].parent) {
        list[pa].parent = list[pb].parent;
    }
};

const NM = input[0].split(' ');
const n = Number(NM[0]);
const m = Number(NM[1]);

const TRUTH = input[1].split(' ');
const t = Number(TRUTH[0]);

const people = [...Array(n + 1)].map((_, i) => {
    let object = {};
    object.id = i;
    object.parent = i;
    object.truth = false;
    return object;
});

if (t !== 0) {
    const knowns = TRUTH.slice(1);
    for (const known of knowns) {
        people[Number(known)].truth = true;
    }
}

for (let i = 2; i < m + 2; i += 1) {
    const raw = input[i].split(' ');
    const _ = Number(raw[0]);
    const list = raw.slice(1);
    const party = {
        id: people.length,
        parent: people.length,
        truth: false,
    };
    
    people.push(party);
    
    for (const person of list) {
        union(people, Number(person),  party.id);
    }
}

for (const obj of people) {
    find(people, obj.id);
}

const count = [...people].filter((x, i) => i > n && !x.truth).length
console.log(count);