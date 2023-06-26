#include <memory>
#include <iterator>
namespace binary_search_tree {
    template <typename T>
    struct binary_tree {
        std::unique_ptr<binary_tree<T>> left_child;
        std::unique_ptr<binary_tree<T>> right_child;
        T node_data;

        std::unique_ptr<binary_tree<T>>& left() {
            if (left_child) {
                return left_child;
            } else {
                // return self if right_child is null
                return *this;
            }
        }

        std::unique_ptr<binary_tree<T>>& right() {
            if (right_child) {
                return right_child;
            } else {
                // return self if right_child is null
                return *this;
            }
        }

        T data() {
            return node_data;
        }

        void insert(T data) {
            if (data < node_data) {
                if (left_child) {
                    left_child->insert(data);
                } else {
                    left_child = std::make_unique<binary_tree<T>>(data);
                }
            } else {
                if (right_child) {
                    right_child->insert(data);
                } else {
                    right_child = std::make_unique<binary_tree<T>>(data);
                }
            }
        }

        binary_tree<T>(T data) {
            left_child = nullptr;
            right_child = nullptr;

            node_data = data;
        }

        binary_tree<T>() {
            left_child = nullptr;
            right_child = nullptr;

            node_data = T();
        }

        struct iterator {
            bool operator==(iterator other) const {
                throw std::logic_error("Function operator== not yet implemented");
            }
            bool operator!=(iterator other) const {
                throw std::logic_error("Function operator!= not yet implemented");
            }

            void operator++() {
                throw std::logic_error("Function operator++ not yet implemented");
            }
            const T& operator*() {
                throw std::logic_error("Function operator* not yet implemented");
            }
            iterator() {
                throw std::logic_error("Function iterator() not yet implemented");
            }
            iterator(binary_tree<T>* tree_pointer) {
                throw std::logic_error("Function iterator(binary_tree<T>* tree_pointer) not yet implemented");
            }
            iterator(const iterator& tree_iterator) {
                throw std::logic_error("Function iterator(const iterator& tree_iterator) not yet implemented");
            }
        };

        const iterator begin() {
            throw std::logic_error("Function begin() not yet implemented");
        }
        const iterator end() {
            throw std::logic_error("Function end() not yet implemented");
        }
    };
}
