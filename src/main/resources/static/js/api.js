export function bootPage() {
    console.log("Página carregada.");
}

export async function getJSON(url) {
    const r = await fetch(url);
    if (!r.ok) throw new Error("Erro ao buscar " + url);
    return await r.json();
}
const API = {
    baseUrl: '/api',

    async get(path, params = {}) {
        const qs = new URLSearchParams(params).toString();
        const url = `${this.baseUrl}${path}${qs ? `?${qs}` : ''}`;
        const res = await fetch(url);

        if (!res.ok) {
            try {
                const err = await res.json();
                throw err;
            } catch (e) {
                throw { message: res.statusText };
            }
        }

        return res.json();
    },

    async send(method, path, body) {
        const res = await fetch(`${this.baseUrl}${path}`, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: body ? JSON.stringify(body) : null,
        });

        if (!res.ok) {
            try {
                const err = await res.json();
                throw err;
            } catch (e) {
                throw { message: res.statusText };
            }
        }

        return res.status === 204 ? null : res.json();
    },

    listPets(params) {
        return this.get('/pets', params);
    },

    getPet(id) {
        return this.get(`/pets/${id}`);
    },

    createPet(payload) {
        return this.send('POST', '/pets', payload);
    },

    updatePet(id, payload) {
        return this.send('PUT', `/pets/${id}`, payload);
    },

    deletePet(id) {
        return this.send('DELETE', `/pets/${id}`);
    },

    listTutores(params) {
        return this.get('/tutores', params);
    },

    listServicos(params) {
        return this.get('/servicos', params);
    },

    listAtendimentos(params) {
        return this.get('/atendimentos', params);
    },
};

// Expose globally if page scripts expect `API` in window
if (typeof window !== 'undefined') window.API = API;